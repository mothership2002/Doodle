type Method = 'GET' | 'POST' | 'PUT' | 'DELETE';
type Header = {
    [key: string]: string | null,
}

export type { Method, Header };