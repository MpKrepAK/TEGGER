import {Component, Input} from '@angular/core';
import {ServerMessageDTO} from "../../domain/dto/serverMessageDTO";

@Component({
  selector: 'app-error-message',
  templateUrl: './error-message.component.html',
  styleUrls: ['./error-message.component.css']
})
export class ErrorMessageComponent {
  @Input() message! : ServerMessageDTO;
}
