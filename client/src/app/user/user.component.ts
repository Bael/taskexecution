import { Component, OnInit } from '@angular/core';
import {UserService} from '../user.service';
import {ActivatedRoute} from '@angular/router';
import {User} from '../user';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  user: User;

  constructor(private userService: UserService, private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(value =>
      this.userService.getUser(value.get('id')).then(user =>  this.user = user))
  }

  onSaveUserFio(value: string) {
    this.user.fio = value;
    this.userService.updateUser(this.user).then(user =>  this.user = user);

  }

  onChangePassword(value: string) {
    this.user.password = value;
    this.userService.updateUser(this.user).then(user =>  this.user = user);

  }
}
