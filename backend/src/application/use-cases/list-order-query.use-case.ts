import { Order } from "@domain/models/order.model";
import { OrderDomain } from "@domain/order.domain";
import { OrderRepository } from "@domain/order.repository";
import { Inject, Injectable } from "@nestjs/common";

@Injectable()
export class ListOrderQueryUseCase {
    constructor( @Inject(OrderRepository) private readonly orderRepository: OrderRepository) {}
    
    async execute(): Promise<Order[]> {
        const orders: OrderDomain[] = await this.orderRepository.findAll();
        return orders.map(order => order.toModel());
    } 
}