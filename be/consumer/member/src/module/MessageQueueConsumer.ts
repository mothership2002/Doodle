import {Kafka} from "kafkajs";
import {BaseModule} from "./Module";
import {inject, injectable} from "inversify";
import {TYPES} from "../config/types";
import {Logger} from "./Logger";

// TODO 카프카로 만들었지만, 메세지 큐가 변경되는 가능성도 열어놓아야 하므로 네이밍방식이 틀렸음.

interface MessageQueueConfig {
    clientId: string;
    brokers: Array<string>;
}

@injectable()
export class MessageQueueConsumer extends BaseModule {

    private readonly topic = 'Member';
    private readonly groupId = 'member';

    constructor(@inject(TYPES.Logger) logger: Logger) {
        super(logger.getLogger());
    }

    private async createMessageQueue() {
        return new Kafka(await super.init(process.env.KAFKA, 'message queue'));
    }

    async run() {
        const consumer = (await this.createMessageQueue()).consumer({groupId: this.groupId});
        await consumer.connect();
        await consumer.subscribe({topic: this.topic, fromBeginning: true});
        this.log.info(`kafka consumer start - topic: ${this.topic}, groupId: ${this.groupId}`);

        await consumer.run({
            eachMessage: async ({topic, partition, message}) => {
                this.log.info(`consume message - topic: ${topic}, partition: ${partition}`);
                if (message?.value?.toString()) {

                }
                // console.log(topic, partition, message?.value?.toString());

            },
        })
    }

}

//
// const run = async (log: winston.Logger) => {
//     const topic = 'Member';
//     const groupId = 'member';
//     const consumer = (await messageQueue(log)).consumer({groupId});
//
//     await consumer.connect();
//     await consumer.subscribe({topic, fromBeginning: true});
//     log.info(`kafka consumer start - topic: ${topic}, groupId: ${groupId}`);
//
//     await consumer.run({
//         eachMessage: async ({topic, partition, message}) => {
//             log.info(`consume message - topic: ${topic}, partition: ${partition}`);
//             if (message?.value?.toString()) {
//
//             }
//             // console.log(topic, partition, message?.value?.toString());
//
//         },
//     })
// }
//
// export default run;