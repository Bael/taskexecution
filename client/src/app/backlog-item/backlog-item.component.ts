import {Component, OnInit} from '@angular/core';
import {BacklogItem} from '../backlog-item';
import {BacklogItemService} from '../backlog-item.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-backlog-item',
  templateUrl: './backlog-item.component.html',
  styleUrls: ['./backlog-item.component.css']
})
export class BacklogItemComponent implements OnInit {

  item: BacklogItem = new BacklogItem(); // {id: 1, name: 'new item ', priority: 2, options: ['to do', 'to do do do']};

  constructor(private service: BacklogItemService,
              private activatedRoute: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    this.activatedRoute.paramMap
      .subscribe(value => this.service.getItem(value.get('id')).then(itemDTO => this.item = itemDTO));
  }

  addItem(value: string) {
    console.log(value);
    this.item.options.push(value);

  }

  saveItem() {
    this.service.updateItem(this.item).then(itemDTO => this.item = itemDTO);
  }

  deleteItem() {
    this.service.deleteItem(this.item).then(() => this.returnToJournal());
  }

  private returnToJournal() {
    this.router.navigateByUrl('/backlog');
  }

  private returnToProjects() {
    this.router.navigateByUrl('/projects');
  }

  convertToProject() {
    this.service.convertToProject(this.item).then(() => this.returnToProjects());

  }
}
