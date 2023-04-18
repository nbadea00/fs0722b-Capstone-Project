import { SelectionModel } from '@angular/cdk/collections';
import {AfterViewInit, Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';

type DataTabel = {
  id: number;
  name:string;
  lead:string;
}

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent implements OnInit, AfterViewInit  {
  dataSource!: MatTableDataSource<DataTabel>;
  displayedColumns: string[] = ['select', 'name', 'lead'];
  dataTabelProps: string[] = ['name', 'lead']
  selection = new SelectionModel<DataTabel>(true, [])
  @Input() type!: string;
  @Input() keys!: string[];
  @Input() elements!: any[];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  @Output() event: EventEmitter<number[]> = new EventEmitter();

  constructor() { }
  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(event: Event) {
    let filterValue = (event.target as HTMLInputElement).value;
    if(filterValue === undefined) filterValue = ''
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  addData(){

  }

  removeData(){
    let numberArray: number[] = this.selection.selected.map(e => e.id);
    this.event.emit(numberArray)
  }

  ngOnInit(): void {
    let args = this.elements.map( e =>  {
      return {
        id: e[this.keys[0]],
        name: e[this.keys[1]],
        lead: e[this.keys[2]].firstname,
      }
    });
    this.dataSource = new MatTableDataSource(args);
  }

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.length;
    return numSelected === numRows;
  }

  toggleAllRows() {
    if (this.isAllSelected()) {
      this.selection.clear();
      return;
    }

    this.selection.select(...this.dataSource.data);
  }

  checkboxLabel(row?: DataTabel): string {
    if (!row) {
      return `${this.isAllSelected() ? 'deselect' : 'select'} all`;
    }
    return `${this.selection.isSelected(row) ? 'deselect' : 'select'} row ${row}`;
  }

}
