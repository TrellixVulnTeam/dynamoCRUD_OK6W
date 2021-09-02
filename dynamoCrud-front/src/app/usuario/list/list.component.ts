import { Usuario } from 'src/app/models/usuario';
import { Component, OnInit } from '@angular/core';
import { UsuarioService } from 'src/app/services/usuario.service';
import { NgxSpinnerService } from 'ngx-spinner';


@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {


  usuarios: Usuario[]= []; 

  textSpinner = '';

  max: number;

  constructor( 
  private usuarioService: UsuarioService,
  private spinner: NgxSpinnerService
  ) { }

  ngOnInit(): void {
    this.onLoad();
  }

  onLoad():void{
    this.textSpinner = 'Loading Users...';
    this.spinner.show();
    this.usuarioService.list().subscribe( data=>{
      this.usuarios=this.orderBy(data);
      this.max = this.getMax();
      this.spinner.hide();
    });

  }

  orderBy(usuarios: Usuario[]):Usuario[] {
    return usuarios.sort((a,b)=> (a.id> b.id ? 1: -1))
  }

  getMax(): number {
    return Math.max(...this.usuarios.map(u => u.id));
  }

  onDelete(id: number):void {
    this.textSpinner = 'Deleting User...';
    this.spinner.show();
    this.usuarioService.delete(id).subscribe(data => {
      this.onLoad();
    });


  }


}
