import {AfterViewInit, Component, Input, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';

type DataTabel = {
  id:number;
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
  displayedColumns: string[] = ['id', 'name', 'lead'];
  @Input() type!: string;
  @Input() keys!: string[];
  @Input() elements!: any[];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  isLoadingResults = true;
  isRateLimitReached = false;

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

}
