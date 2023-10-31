import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './components/root/app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CardComponent } from './components/card/card.component';
import {MocCardService} from "./services/moc-card.service";
import {AppRoutingModule} from "./app-routing.module";
import { SignUpComponent } from './components/auth/sign-up/sign-up.component';
import { SignInComponent } from './components/auth/sign-in/sign-in.component';
import { CatalogComponent } from './components/catalog/catalog.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { TranslatePipe } from './pipes/translate.pipe';
import {AppDataService} from "./services/app-data.service";
import { TagRowComponent } from './components/dataRows/tag-row/tag-row.component';
import { AdminMainComponent } from './components/admin/admin-main/admin-main.component';
import { AdminOneFieldsComponent } from './components/admin/admin-one-fields/admin-one-fields.component';
import { HeaderComponent } from './components/header/header.component';
import {HttpClientModule} from "@angular/common/http";
import {OneFieldService} from "./services/repositories/oneFieldService";
import { ErrorMessageComponent } from './components/error-message/error-message.component';
import { InfoStringRowComponent } from './components/dataRows/info-string-row/info-string-row.component';
import { UserProductComponent } from './components/user-product/user-product.component';

@NgModule({
  declarations: [
    AppComponent,
    CardComponent,
    SignUpComponent,
    SignInComponent,
    CatalogComponent,
    TranslatePipe,
    TagRowComponent,
    AdminMainComponent,
    AdminOneFieldsComponent,
    HeaderComponent,
    ErrorMessageComponent,
    InfoStringRowComponent,
    UserProductComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    MocCardService,
    AppDataService,
    OneFieldService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
