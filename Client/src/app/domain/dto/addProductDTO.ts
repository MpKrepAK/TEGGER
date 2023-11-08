import {ProductInfoBooleanDTO} from "./productInfoBooleanDTO";
import {ProductInfoNumberDTO} from "./productInfoNumberDTO";
import {ProductInfoStringDTO} from "./productInfoStringDTO";
import {OneFieldEntityDTO} from "./oneFieldEntityDTO";

export interface AddProductDTO{
  cost: number;
  id : number,
  typeName : string,
  manufacturerName : string,
  count : number,
  rating : number,
  mainImage : string,
  title : string,
  info : string,
  images : string[],
  infoBooleans : OneFieldEntityDTO[],
  infoNumbers : OneFieldEntityDTO[],
  infoStrings : OneFieldEntityDTO[],
  tags : OneFieldEntityDTO[]
}
