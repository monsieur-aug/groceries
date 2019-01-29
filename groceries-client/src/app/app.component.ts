import { Component } from '@angular/core';
import { Product } from './search-results/product.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  retrievedProducts : Product[];

  constructor() {
    let apples : any = {
      id: 321654,
      description: 'apples',
      lastSoldDate: '2017-9-7',
      shelfLifeDays: 7,
      department: 'Produce',
      price: 1.49,
      unit: 'lb',
      xFor: 1,
      cost: 0.2
    };

    let headacheMedicine : any = {
      id: 9000001,
      description: 'headache medicine',
      lastSoldDate: '2017-9-20',
      shelfLifeDays: 365,
      department: 'Pharmacy',
      price: 5.89,
      unit: 'Each',
      xFor: 1,
      cost: 1.9
    };

    let cereal : any = {
      id: 119290,
      description: 'cereal',
      lastSoldDate: '2017-9-18',
      shelfLifeDays: 40,
      department: 'Grocery',
      price: 3.19,
      unit: 'Each',
      xFor: 1,
      cost: 0.19
    }; 

    this.retrievedProducts = [ apples, headacheMedicine, cereal ];
  }
}
