import { Component, OnInit } from '@angular/core';
import {MatTableDataSource} from "@angular/material";

export interface UserList {
  nome: string;
}

const ELEMENT_DATA: UserList[] = [
  {nome: 'Adriano'},
  {nome: 'Maria'},
  {nome: 'Renata'},
  {nome: 'Pedro'},
  {nome: 'João'},
  {nome: 'Julia'},
  {nome: 'Adriano'},
  {nome: 'Maria'},
  {nome: 'Renata'},
  {nome: 'Pedro'},
  {nome: 'João'},
  {nome: 'Julia'},
  {nome: 'Adriano'},
  {nome: 'Maria'},
  {nome: 'Renata'},
  {nome: 'Pedro'},
  {nome: 'João'},
  {nome: 'Julia'},
];

@Component({
  selector: 'drp-usuarios-lista',
  templateUrl: './usuarios-lista.component.html',
  styleUrls: ['./usuarios-lista.component.scss']
})
export class UsuariosListaComponent implements OnInit {

  displayedColumns: string[] = ['nome'];
  dataSource = new MatTableDataSource(ELEMENT_DATA);

  constructor() { }

  ngOnInit() {
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
