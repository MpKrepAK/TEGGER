import {Component, Input} from '@angular/core';
import {OneFieldEntityDTO} from "../../domain/dto/oneFieldEntityDTO";

@Component({
  selector: 'app-seller-product-info',
  templateUrl: './seller-product-info.component.html',
  styleUrls: ['./seller-product-info.component.css']
})
export class SellerProductInfoComponent {
  @Input() values : OneFieldEntityDTO[] = [];
  @Input() type = '';
  public selectedValue = 0;
  public inputValue = '';

}
