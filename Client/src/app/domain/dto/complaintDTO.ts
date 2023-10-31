import {ComplaintTypeDTO} from "./complaintTypeDTO";

export interface ComplaintDTO{
  type : ComplaintTypeDTO,
  message : string,
  productId : number
}
