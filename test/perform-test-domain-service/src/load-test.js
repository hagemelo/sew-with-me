import http from 'k6/http';
import { sleep, group, check } from 'k6';
import { Trend, Rate, Counter } from 'k6/metrics';

const trend =  new Trend('ResponseTime');
const counter = new Counter('QtdRequest');
const failureRate =  new Rate('failureRate');

const TARGET_HOSTNAME = __ENV.TARGET_HOST;

export default function () {

    group('order/newOrder', () => {
        const url = `${TARGET_HOSTNAME}/order/newOrder`;
        const headers =  {
            'Content-Type': `application/json;charset=UTF-8`,
        };
        const body = JSON.stringify({
            deliveryForecast: "05/06/2024",
            value: 25.0
        });

        const params = {
            headers,
            body
        };

        const response = http.post(url, params);

        failureRate.add(!check(response, { 'status was 200': (resp) => {
            if (resp.status !== 200) {
              return false;
            }
            trend.add(resp.timings.duration);
            counter.add(1);
            return true;
          }}));
          sleep(1);
    });
}

