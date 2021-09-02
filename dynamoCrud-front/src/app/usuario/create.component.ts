import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { Usuario } from '../models/usuario';
import { UsuarioService } from '../services/usuario.service';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {

  name = '';
  lastName = '';
  max: number;
  usuario: Usuario = null;
  textSpinner = '';


  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private usuarioService: UsuarioService,
    private spinner: NgxSpinnerService


  ) { }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe( data =>{
      this.max = data.max;
  })
  }

  onCreate(): void{
    this.max++;
    this.usuario = new Usuario(this.max, this.name, this.lastName);
    this.textSpinner = 'Creating User....';
    this.spinner.show();
    this.usuarioService.write(this.usuario).subscribe( data =>{
      this.spinner.hide();
      this.router.navigate(['/']);
  });
  }
}
