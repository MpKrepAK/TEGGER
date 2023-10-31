import {Component, Input} from '@angular/core';
import {CardDTO} from "../../domain/dto/cardDTO";

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent {
  @Input() product!: CardDTO;

}
