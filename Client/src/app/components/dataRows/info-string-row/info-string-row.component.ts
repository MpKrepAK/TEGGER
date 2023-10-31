import {Component, EventEmitter, Input, Output} from '@angular/core';
import {ProductTagDTO} from "../../../domain/dto/productTagDTO";
import {OneFieldEntityDTO} from "../../../domain/dto/oneFieldEntityDTO";

@Component({
  selector: 'app-info-string-row',
  templateUrl: './info-string-row.component.html',
  styleUrls: ['./info-string-row.component.css', '../../../../styles/admin.css']
})
export class InfoStringRowComponent {
  @Input() entity! : OneFieldEntityDTO;
  @Output() returned = new EventEmitter<OneFieldEntityDTO>();
  @Output() id = new EventEmitter<number>();

  returnDTO(){
    this.returned.emit(this.entity);
  }
  returnId(){
    this.id.emit(this.entity.id);
  }
}
