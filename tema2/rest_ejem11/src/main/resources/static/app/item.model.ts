import 'angular2/core'; //Hack to avoid compilation error

export interface Item {
	id?: number;
	description: string;
	checked: boolean;
}
