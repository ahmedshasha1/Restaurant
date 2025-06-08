import {Products} from './products';

export class CartOrder {
  id: number;
  name: string;
  logoPath: string;
  price: number;
  description: string;
  quantity : number;

  constructor(private products:Products) {
    this.id = products.id;
    this.name = products.name;
    this.logoPath = products.logoPath;
    this.description = products.description;
    this.price = products.price;
    this.quantity = 1;
  }
}
