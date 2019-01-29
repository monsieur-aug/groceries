import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  isMoreOptionsExpanded : boolean = false;

  constructor() { }

  ngOnInit() {
  }

  toggleMoreSearchOptions() {
    this.isMoreOptionsExpanded = !this.isMoreOptionsExpanded;
  }
}
