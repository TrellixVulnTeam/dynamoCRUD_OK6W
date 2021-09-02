import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Usuario } from '../models/usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  usuarioURL =environment.usuarioURL;

  constructor(private httpClient: HttpClient) { }

  public list(): Observable <Usuario[]>{
    return this.httpClient.get<Usuario[]>(this.usuarioURL)
  }

  public write(usuario: Usuario): Observable<Usuario>{
    return this.httpClient.post<Usuario>(this.usuarioURL, usuario);
  }

  public delete(id: number):Observable<Usuario> {
    return this.httpClient.delete<Usuario>(this.usuarioURL + '${id}')
  }


}
