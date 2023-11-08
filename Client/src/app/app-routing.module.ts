import {NgModule, OnInit} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {AppComponent} from "./components/root/app.component";
import {SignInComponent} from "./components/auth/sign-in/sign-in.component";
import {CatalogComponent} from "./components/catalog/catalog.component";
import {FormControl, FormGroup} from "@angular/forms";
import {SignUpComponent} from "./components/auth/sign-up/sign-up.component";
import {AdminMainComponent} from "./components/admin/admin-main/admin-main.component";
import {AdminOneFieldsComponent} from "./components/admin/admin-one-fields/admin-one-fields.component";
import {UserProductComponent} from "./components/user-product/user-product.component";
import {SellerProductComponent} from "./components/seller-product/seller-product.component";

const routes : Routes = [
  {path : '', redirectTo : 'signup', pathMatch: "full"},
  {path : 'catalog', component : CatalogComponent},
  {path : 'signin', component : SignInComponent},
  {path : 'signup', component : SignUpComponent},
  {path : 'admin', component : AdminMainComponent},
  {path : 'admin/one-field/:activity/:title', component : AdminOneFieldsComponent},
  {path : 'product', component : UserProductComponent},
  {path : 'create', component : SellerProductComponent},

]


@NgModule({
  imports : [RouterModule.forRoot(routes)],
  exports : [RouterModule]
})

export class AppRoutingModule {

}
