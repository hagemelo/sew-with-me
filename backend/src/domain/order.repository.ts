import { OrderDomain } from "./order.domain";

export  interface OrderRepository {
   
    findAll(): Promise<OrderDomain[]>;
}

export const OrderRepository = Symbol('OrderRepository')