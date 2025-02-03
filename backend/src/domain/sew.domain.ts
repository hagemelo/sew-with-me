import { MaterialUsed } from "./models/material-used.model";
import { Order } from "./models/order.model";
import { SewStatus } from "./models/sew-status.model";
import { Sew } from "./models/sew.model";
import { TypeSew } from "./models/type-sew.model";

export class SewDomain implements Sew {
    constructor(
        private readonly props: Sew,
    ) { }

    get id(): number {
        return this.props.id;
    }   

    get type(): TypeSew {       
        return this.props.type;
    }
    
    get status(): SewStatus {       
        return this.props.status;
    }

    get describe(): string {       
        return this.props.describe;
    }

    get deliveryForecast(): Date {       
        return this.props.deliveryForecast;
    }   

    get value(): number {       
        return this.props.value;
    }   

    get materials(): MaterialUsed[] {       
        return this.props.materials;
    }

    get order(): Order {       
        return this.props.order;
    }

    toModel(): Sew {
        return this.props;
    }
}