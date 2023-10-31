import {AppDataService} from "../app-data.service";
import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {ProductTagDTO} from "../../domain/dto/productTagDTO";
import {Observable} from "rxjs";
import {ServerMessageDTO} from "../../domain/dto/serverMessageDTO";
import {OneFieldEntityDTO} from "../../domain/dto/oneFieldEntityDTO";

@Injectable({
  providedIn: 'root'
})
export class OneFieldService {
  constructor(private appData : AppDataService,
              private http : HttpClient) {
  }

  public path = "";

  getPage(page : number, name : string) : Observable<OneFieldEntityDTO[]>{
    return this.http.get<OneFieldEntityDTO[]>(this.appData.serverUrl+this.path+`/${page}?name=${name}`);
  }

  create(info : OneFieldEntityDTO) : Observable<ServerMessageDTO> {
    return this.http.post<ServerMessageDTO>(this.appData.serverUrl+this.path, info);
  }

  update(info: OneFieldEntityDTO) {
    return this.http.put<ServerMessageDTO>(this.appData.serverUrl+this.path, info);
  }

  delete(id: number) {
    return this.http.delete<ServerMessageDTO>(this.appData.serverUrl+this.path+'/'+id);
  }

  getPageCount(name : string){
    return this.http.get<number>(this.appData.serverUrl+this.path+`?name=${name}`);
  }
}
