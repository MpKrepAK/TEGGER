import {ProductInfoBooleanDTO} from "./productInfoBooleanDTO";
import {ProductInfoNumberDTO} from "./productInfoNumberDTO";
import {ProductInfoStringDTO} from "./productInfoStringDTO";

export interface ProductDTO{
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
  infoBooleans : ProductInfoBooleanDTO[],
  infoNumbers : ProductInfoNumberDTO[],
  infoStrings : ProductInfoStringDTO[]
}
