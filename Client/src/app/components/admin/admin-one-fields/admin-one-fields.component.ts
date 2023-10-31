import {Component} from '@angular/core';
import {OneFieldService} from "../../../services/repositories/oneFieldService";
import {ProductTagDTO} from "../../../domain/dto/productTagDTO";
import {EVisibleData} from "../../../domain/ui/eVisibleData";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ServerMessageDTO} from "../../../domain/dto/serverMessageDTO";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-admin-one-fields',
  templateUrl: './admin-one-fields.component.html',
  styleUrls: ['./admin-one-fields.component.css', '../../../../styles/admin.css']
})
export class AdminOneFieldsComponent {
  protected readonly EVisibleData = EVisibleData;

  pageCount : number = 0;
  visibleData : EVisibleData = EVisibleData.NONE;
  entities : ProductTagDTO[] = [];
  pageNumber = 0;
  error : ServerMessageDTO = {
    message:'',
    statusCode:0
  };
  title = "Ошибка";
  constructor(private route: ActivatedRoute, public service : OneFieldService) {
    this.route.params.subscribe(params => {
      const path = params['activity'];
      this.title  = params['title'];
      this.service.path = path;
    });
    this.getPage();
  }

  getPageCount(){
    this.service.getPageCount(this.searchForm.get('name')?.value).subscribe(x=>{
      this.pageCount = x;
      console.log(x);
    });
  }

  createForm : FormGroup = new FormGroup({
    name : new FormControl("", [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(255)
    ])
  });

  searchForm : FormGroup = new FormGroup({
    name : new FormControl("")
  });

  updateForm : FormGroup = new FormGroup({
    id : new FormControl(0),
    name : new FormControl("", [
      Validators.required,
      Validators.minLength(3),
      Validators.maxLength(255)
    ])
  });

  getPage(){
    this.getPageCount();
    this.service.getPage(this.pageNumber, this.searchForm.get("name")?.value).subscribe(x=>{
      this.entities = x;
    })
  }

  create() {
    this.getPageCount();
    let tag : ProductTagDTO = {
      id : 0,
      name : this.createForm.get("name")?.value
    };
    this.service.create(tag).subscribe(x=>{
      this.viewError(x);
      this.getPage();
    });
  }

  update() {
    this.getPageCount();
    let tag : ProductTagDTO = {
      id : this.updateForm.get("id")?.value,
      name : this.updateForm.get("name")?.value
    };
    console.log(tag);
    this.service.update(tag).subscribe(x=>{
      this.viewError(x);
      this.getPage();
    });
  }

  delete(id : number){
    this.getPageCount();
    this.service.delete(id).subscribe(x=>{
      this.viewError(x);
      this.getPage();
    });
  }

  search() {
    this.pageNumber = 0;
    this.getPage();
  }
  viewUpdate(dto : ProductTagDTO){
    console.log(dto);
    this.updateForm.patchValue({
      id:dto.id,
      name: dto.name
    });
    this.visibleData = EVisibleData.INFO;
  }

  viewError(error : ServerMessageDTO){
    this.error.statusCode = error.statusCode;
    this.error.message = error.message;
    setTimeout(()=>{
      this.error.message = '';
      this.error.statusCode = 0;
    }, 3000);
  }

  nextPage() {
    if (this.pageNumber+1>this.pageCount-1){
      this.pageNumber=0;
      this.getPage();
      return;
    }

    if (this.pageNumber+1<this.pageCount){
      this.pageNumber++;
      this.getPage();
      return
    }

  }
  previousPage(){

    if (this.pageNumber-1<0){
      this.pageNumber=this.pageCount-1;
      this.getPage();
      return;
    }

    if (this.pageNumber-1>=0){
      this.pageNumber--;
      this.getPage();
      return;
    }

  }
}
