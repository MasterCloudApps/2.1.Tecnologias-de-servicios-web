import {Component, OnInit} from '@angular/core';
import {HTTP_PROVIDERS} from '@angular/http';
import {Http} from '@angular/http';
import {Item} from './item.model';

@Component({
	selector: 'my-app',
	templateUrl: 'app/app.component.html',
	providers: [HTTP_PROVIDERS]
})
export class AppComponent implements OnInit {

	private items: Item[] = [];

	constructor(private http:Http){}

	ngOnInit(){
		this.refresh();
	}
	
	onCheckboxChange(inputChecked: boolean, item: Item){
		item.checked = inputChecked;
		this.updateItem(item);
	}

	private refresh(){
		this.http.get('/items/').subscribe(
			response => this.items = response.json(),
			error => this.handleError(error)
		);
	}

	addItem(description: string){

		let item = {description, checked:false};

		this.http.post('/items/', item).subscribe(
			response => this.refresh(),
			error => this.handleError(error)
		);
	}

	removeItem(item: Item){

		this.http.delete('/items/' + item.id).subscribe(
			response => this.refresh(),
			error => this.handleError(error)
		);
	}

	updateItem(item: Item){

		this.http.put('/items/' + item.id, item).subscribe(
			response => this.refresh(),
			error => this.handleError(error)
		);
	}

  private handleError(error: any){
    console.error(error);
  }
}
