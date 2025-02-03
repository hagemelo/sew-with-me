import { Client } from "./models/client.model";
import { OrderStatus } from "./models/order-status.model";
import { Order } from "./models/order.model";
import { Sew } from "./models/sew.model";

export class OrderDomain implements Order {
    constructor(
        private readonly props: Order,
    ) { }

    public get id(): number {
        return this.props.id
    }   

    public get client(): Client {   
        return this.props.client
    }   

    public get created_at(): Date {   
        return this.props.created_at
    }   

    public get value(): number {   
        return this.props.value
    }   

    public get status(): OrderStatus {
        return this.props.status
    }

    public get deliveryForecast(): Date {
        return this.props.deliveryForecast
    }

    public get sews(): Sew[] {
        return this.props.sews
    }

    toModel(): Order {
        return this.props;
    }
}