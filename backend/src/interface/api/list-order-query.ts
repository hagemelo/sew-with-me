import { ListOrderQueryUseCase } from "@application/use-cases/list-order-query.use-case";
import { Order } from "@domain/models/order.model";
import { Controller, Get, Inject, Injectable } from "@nestjs/common";

@Injectable()
@Controller('order')
export class ListOrderQuery {
    constructor(
        @Inject(ListOrderQueryUseCase) private readonly listOrderQueryUseCase: ListOrderQueryUseCase,
    ) { }

    @Get('/list-all')
    async execute(): Promise<Order[]> {
        return await this.listOrderQueryUseCase.execute();
    }
}