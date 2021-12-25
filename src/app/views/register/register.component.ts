import { Component } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-dashboard',
  templateUrl: 'register.component.html'
})
export class RegisterComponent {

  registerForm: FormGroup = this.formBuilder.group({
    firstName: ['', Validators.required],
    lastName: [''],
    username: ['', [Validators.compose( [Validators.required, Validators.minLength(6)])]],
    email: ['', [Validators.compose( [Validators.required, Validators.email])]],
    password: ['', [Validators.compose( [Validators.required, Validators.minLength(6)])]],
    retypePassword: ['', [Validators.compose(
      [Validators.required, this.validateAreEqual.bind(this)]
    )]],
  })

  constructor(private formBuilder: FormBuilder) { }

  get formValue(){
    return this.registerForm.controls;
  }

  private validateAreEqual(fieldControl: FormControl){
    if(this.registerForm)
      return fieldControl.value === this.registerForm.get('password')? null: {
        NotEqual: true
      };
  }
}
