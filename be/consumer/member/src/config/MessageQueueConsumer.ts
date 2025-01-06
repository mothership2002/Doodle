import {Kafka} from "kafkajs";
import {resolve} from "path";
import winston from "winston";

// TODO 카프카로 만들었지만, 메세지 큐가 변경되는 가능성도 열어놓아야 하므로 네이밍방식이 틀렸음.

interface MessageQueueConfig {
    clientId: string;
    brokers: Array<string>;
}

const init = async (log: winston.Logger): Promise<MessageQueueConfig> => {
    const messageQueueInfoPath = process.env.KAFKA;
    if (messageQueueInfoPath) {
        const path = resolve(__dirname, '../' + messageQueueInfoPath);
        log.info(`Message Queue info path: ${path}`)
        return await import(path);
    } else {
        log.error('Message Queue environment variable is not defined.');
        throw new Error('Message Queue environment variable is not defined.');
    }
}

const messageQueue = async (log: winston.Logger) => new Kafka(await init(log));


const run = async (log: winston.Logger) => {
    const topic = 'Member';
    const groupId = 'member';
    const consumer = (await messageQueue(log)).consumer({groupId});

    await consumer.connect();
    await consumer.subscribe({topic, fromBeginning: true});
    log.info(`kafka consumer start - topic: ${topic}, groupId: ${groupId}`);

    await consumer.run({
        eachMessage: async ({topic, partition, message}) => {
            log.info(`consume message - topic: ${topic}, partition: ${partition}`);
            if (message?.value?.toString()) {

            }
            // console.log(topic, partition, message?.value?.toString());

        },
    })
}

export default run;