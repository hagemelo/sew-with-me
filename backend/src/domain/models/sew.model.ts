import { MaterialUsed } from "./material-used.model";
import { Order } from "./order.model";
import { SewStatus } from "./sew-status.model";
import { TypeSew } from "./type-sew.model";

export interface Sew {

    id?: number;
	type: TypeSew;
	status: SewStatus;
    describe: string;
    deliveryForecast: Date;
    value?: number;
	materials?: MaterialUsed[];
    order?: Order;
}