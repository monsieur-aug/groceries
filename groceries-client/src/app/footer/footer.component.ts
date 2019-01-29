import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  message : string = "H-E-B Developer II Coding Challenge";
  
  constructor() { }

  ngOnInit() {
  }

}
