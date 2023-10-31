import {Component, OnInit} from '@angular/core';
import {UiData} from "../../domain/ui/uiData";
import {MocCardService} from "../../services/moc-card.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers : [MocCardService]
})
export class AppComponent{
  title = 'Client';

}
