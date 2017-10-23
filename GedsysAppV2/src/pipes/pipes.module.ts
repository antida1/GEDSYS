import { NgModule } from '@angular/core';
import { MomentPipe } from './moment/moment';
import { OrderByPipe } from './order-by/order-by';
@NgModule({
	declarations: [MomentPipe,
    OrderByPipe],
	imports: [],
	exports: [MomentPipe,
    OrderByPipe]
})
export class PipesModule {}
