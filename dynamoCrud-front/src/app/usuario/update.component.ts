import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { Usuario } from '../models/usuario';
import { UsuarioService } from '../services/usuario.service';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {

  usuario: Usuario = null;
  textSpinner = '';

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private usuarioService: UsuarioService,
    private spinner: NgxSpinnerService
  ) { }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe( user =>
      this.usuario = new Usuario(user.id, user.name, user.lastName)
    )
  };

  onUpdate(): void {
    this.textSpinner = 'Updating user...'
    this.spinner.show();
    this.usuarioService.write(this.usuario).subscribe( data =>{
      this.spinner.hide();
      this.router.navigate(['/'])
    });
  }

}
