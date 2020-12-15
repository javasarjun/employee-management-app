import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { SlimLoadingBarModule } from 'ng2-slim-loading-bar';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { materialModules } from './app.material';

export const importsRoot = [
  BrowserModule,
  AppRoutingModule,
  SlimLoadingBarModule,
  HttpClientModule,
  BrowserAnimationsModule,
  ...materialModules,
  FormsModule,
  ReactiveFormsModule,
];
