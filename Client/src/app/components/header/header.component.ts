import {Component, OnInit} from '@angular/core';
import {UiData} from "../../domain/ui/uiData";
import {MocCardService} from "../../services/moc-card.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit{
  uiData! : UiData;
  constructor(public mocCard : MocCardService) {
  }
  ngOnInit(): void {
    this.uiData = {
      userAccountPopUpOptionsVisibility : false
    }
  }
}
