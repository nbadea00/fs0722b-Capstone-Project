import { Component, Input, OnInit, TemplateRef } from '@angular/core';

@Component({
  selector: 'app-grid-sistem',
  templateUrl: './grid-sistem.component.html',
  styleUrls: ['./grid-sistem.component.scss']
})
export class GridSistemComponent implements OnInit {

  constructor() { }

  @Input() element: any;
  @Input() tableTemplate!: TemplateRef<any>;

  ngOnInit(): void {
  }

}
