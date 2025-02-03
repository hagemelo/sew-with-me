import { OrderRepository } from "@domain/order.repository";
import { Order } from "@domain/models/order.model";
import { Injectable } from "@nestjs/common";
import { InjectRepository } from "@nestjs/typeorm";
import { Repository } from "typeorm";
import { OrderEntity } from "@infrastructure/entities/order.entity";
import { OrderDomain } from "@domain/order.domain";

@Injectable()
export class OrderImplRepository implements OrderRepository {

    constructor(
        @InjectRepository(OrderEntity)
        private readonly repository: Repository<OrderEntity>,
      ) {}

    async findAll(): Promise<OrderDomain[]> {
        const orders = await this.repository.find();
        return orders.map(order => new OrderDomain(order));
    }
}