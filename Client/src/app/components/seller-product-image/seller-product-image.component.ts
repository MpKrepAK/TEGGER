import {Component, EventEmitter, Output} from '@angular/core';

@Component({
  selector: 'app-seller-product-image',
  templateUrl: './seller-product-image.component.html',
  styleUrls: ['./seller-product-image.component.css']
})
export class SellerProductImageComponent {
  public image = '';

  onFileSelected(event: any) {
    const file = event.target.files[0]
    if (file) {
      this.convertImageToBase64(file);
    }
  }


  convertImageToBase64(file: File) {
    const reader = new FileReader();

    reader.onload = (e: any) => {
      const base64String = e.target.result;
      this.image = base64String;
    };

    reader.readAsDataURL(file);
  }
}
