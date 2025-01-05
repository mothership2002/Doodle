import {Kafka} from "kafkajs";
import {resolve} from "path";

interface KafkaInfo {
    clientId: string;
    brokers: Array<string>;
}

const init = async (): Promise<KafkaInfo> => {
    const kafkaPath = process.env.KAFKA;
    if (kafkaPath) {
        const path = resolve(__dirname, '../' + kafkaPath);
        console.log(`Kafka info path: ${path}`)
        return await import(path);
    } else {
        throw new Error('DATABASE environment variable is not defined.');
    }
}

const kafka = async () => new Kafka(await init());


const run = async () => {
    const consumer = (await kafka()).consumer({groupId: 'member'});

    await consumer.connect();
    await consumer.subscribe({topic: 'member', fromBeginning: true});
    console.log(`kafka consumer start - topic: member, groupId: member`);

    await consumer.run({
        eachMessage: async ({topic, partition, message}) => {
            console.log(topic, partition, message?.value?.toString());
        },
    })
}

export default run;