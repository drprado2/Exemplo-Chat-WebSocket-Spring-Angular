import {MatButtonModule, MatCheckboxModule, MatCardModule, MatIconModule, MatProgressSpinnerModule, MatFormFieldModule, MatInputModule} from '@angular/material';
import {NgModule} from "@angular/core";

@NgModule({
    imports: [MatButtonModule, MatCheckboxModule, MatCardModule, MatIconModule, MatProgressSpinnerModule, MatFormFieldModule, MatInputModule],
    exports: [MatButtonModule, MatCheckboxModule, MatCardModule, MatIconModule, MatProgressSpinnerModule, MatFormFieldModule, MatInputModule],
})
export class AngularMaterialModule { }