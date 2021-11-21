import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../../security/authentication/authentication.service";
import {ActivatedRoute, Route, Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Token} from "../../models/token";

@Component({
  selector: 'app-dashboard',
  templateUrl: 'login.component.html'
})
export class LoginComponent implements OnInit{
  username!:string;
  password!:string;
  routeUrl!: string;

  loginForm: FormGroup  = this.formBuilder.group({
    username: ['', Validators.required],
    password: ['', Validators.required]
  });

  constructor(private formBuilder: FormBuilder, private authenticationService: AuthenticationService, private router: Router, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.routeUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  get formValue(){
    return this.loginForm.controls;
  }

  login(): void{
    if(this.loginForm.valid){
      this.authenticationService.login(this.formValue.username.value, this.formValue.password.value)
        .subscribe((res:Token)=>{
          console.log('authentication response');
          console.log(res);
          localStorage.setItem('token', res.token);
          console.log(this.routeUrl);
          this.router.navigate([this.routeUrl]);
        })
    }
  }
}
