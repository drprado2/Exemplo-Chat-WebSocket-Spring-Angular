import {MatButtonModule,
    MatCheckboxModule,
    MatCardModule,
    MatIconModule,
    MatProgressSpinnerModule,
    MatFormFieldModule,
    MatInputModule,
    MatSnackBarModule,
    MatTableModule,
    MatListModule,
    MatRadioModule} from '@angular/material';
import {NgModule} from "@angular/core";

@NgModule({
    imports: [MatButtonModule, MatCheckboxModule, MatCardModule, MatIconModule, MatProgressSpinnerModule, MatFormFieldModule, MatInputModule, MatSnackBarModule, MatTableModule, MatListModule, MatRadioModule],
    exports: [MatButtonModule, MatCheckboxModule, MatCardModule, MatIconModule, MatProgressSpinnerModule, MatFormFieldModule, MatInputModule, MatSnackBarModule, MatTableModule, MatListModule, MatRadioModule],
})
export class AngularMaterialModule { }