import {Component, EventEmitter, Input, Output} from '@angular/core';
import {ProductTagDTO} from "../../../domain/dto/productTagDTO";

@Component({
  selector: 'app-tag-row',
  templateUrl: './tag-row.component.html',
  styleUrls: ['./tag-row.component.css', '../../../../styles/admin.css']
})
export class TagRowComponent {
  @Input() entity! : ProductTagDTO;
  @Output() returned = new EventEmitter<ProductTagDTO>();
  @Output() id = new EventEmitter<number>();

  returnDTO(){
    this.returned.emit(this.entity);
  }
  returnId(){
    this.id.emit(this.entity.id);
  }

}
