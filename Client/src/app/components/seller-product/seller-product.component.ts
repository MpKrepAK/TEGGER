import {Component, ElementRef, QueryList, ViewChild, ViewChildren} from '@angular/core';
import {OneFieldEntityDTO} from "../../domain/dto/oneFieldEntityDTO";
import {SellerProductInfoComponent} from "../seller-product-info/seller-product-info.component";
import {ProductDTO} from "../../domain/dto/productDTO";
import {SellerProductImageComponent} from "../seller-product-image/seller-product-image.component";
import {AddProductDTO} from "../../domain/dto/addProductDTO";
import {ProductTagDTO} from "../../domain/dto/productTagDTO";
import {HttpClient} from "@angular/common/http";
import {AppDataService} from "../../services/app-data.service";

@Component({
  selector: 'app-seller-product',
  templateUrl: './seller-product.component.html',
  styleUrls: ['./seller-product.component.css']
})
export class SellerProductComponent {

  constructor(private appData : AppDataService, private http : HttpClient) {

    this.http.get<OneFieldEntityDTO[]>(this.appData.serverUrl + "/admin/info-strings/all").subscribe(x=>{
      this.q1 = x;
    });
    this.http.get<OneFieldEntityDTO[]>(this.appData.serverUrl + "/admin/info-numbers/all").subscribe(x=>{
      this.q2 = x;
    });
    this.http.get<OneFieldEntityDTO[]>(this.appData.serverUrl + "/admin/info-booleans/all").subscribe(x=>{
      this.q3 = x;
    });
  }

  mainImage = '';

  product : AddProductDTO = {
    cost: 0,
    count: 0,
    id: 0,
    images: [],
    info: "",
    infoBooleans: [],
    infoNumbers: [],
    infoStrings: [],
    mainImage: "",
    manufacturerName: "",
    rating: 0,
    tags: [],
    title: "",
    typeName: ""

  }

  @ViewChildren('strings') strings! : QueryList<SellerProductInfoComponent>;
  @ViewChildren('numbers') numbers! : QueryList<SellerProductInfoComponent>;
  @ViewChildren('booleans') booleans! : QueryList<SellerProductInfoComponent>;
  @ViewChildren('image') images! : QueryList<SellerProductImageComponent>;

  types : OneFieldEntityDTO[]=[
    {id : 1, name : "qwe"},
    {id : 2, name : "asd"},
  ];

  tags : ProductTagDTO[] = [];

  q1 : OneFieldEntityDTO[] = [];
  q2 : OneFieldEntityDTO[] = [];
  q3 : OneFieldEntityDTO[] = [];

  manufacturers : OneFieldEntityDTO[]=[];
  stringItemsCount: number = 0;
  numberItemsCount: number = 0;
  booleanItemsCount: number = 0;

  onMainImageSelected(event: any) {
    const file = event.target.files[0];
    if (file) {
      this.convertImageToBase64(file);
    }
  }
  convertImageToBase64(file: File) {
    const reader = new FileReader();

    reader.onload = (e: any) => {
      const base64String = e.target.result;
      console.log(base64String);
      this.mainImage = base64String;
    };

    reader.readAsDataURL(file);
  }

  getArray(n: number): any[] {
    return Array.from({length: n}, (_, index) => index);
  }

  add() {
    console.log(this.strings.get(0)?.inputValue);
    this.product.mainImage = this.mainImage;
    this.product.images = [];
    this.product.infoBooleans = [];
    this.product.infoStrings = [];
    this.product.infoNumbers = [];

    for (let image of this.images){
      if (image.image!==''){
        this.product.images.push(image.image);
      }
    }

    for (let info of this.strings){
      if (info.inputValue!==''){
        this.product.infoStrings.push({id: info.selectedValue, name: info.inputValue});
      }
    }

    for (let info of this.numbers){
      if (info.inputValue!==''&&Number(info.inputValue)>0) {
        this.product.infoNumbers.push({id: info.selectedValue, name: info.inputValue});
      }
    }

    for (let info of this.booleans){
      this.product.infoBooleans.push({id: info.selectedValue, name: info.inputValue});
    }

    console.log(this.product)
    // const container = this.strings.nativeElement;
    // const elems = container.querySelectorAll('app-seller-product-info');
    // elems.forEach((el:any)=>{
    //
    // });
  }
}
