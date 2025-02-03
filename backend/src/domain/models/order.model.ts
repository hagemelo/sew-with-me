import { Client } from "./client.model";
import { OrderStatus } from "./order-status.model";
import { Sew } from "./sew.model";

export interface Order {

    id?: number
    client?: Client
    created_at: Date;
	value?: number;
    status: OrderStatus;
    deliveryForecast: Date;
    sews?: Sew[]
}