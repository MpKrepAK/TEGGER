import {Component, OnInit} from '@angular/core';
import {UiData} from "../../domain/ui/uiData";
import {MocCardService} from "../../services/moc-card.service";

@Component({
  selector: 'app-catalog',
  templateUrl: './catalog.component.html',
  styleUrls: ['./catalog.component.css']
})
export class CatalogComponent{
  constructor(public mocCard : MocCardService) {
  }
}
