export interface post {
    success?: boolean,
    post?: {
        id?: number,
        title?: string,
        likes?: number,
        dislikes?: number,
        date?: string,
        type?: number,
        topic?: {
            id?: number,
            title?: string
        },
        user?: {
            id?: number,
            username?: string
        },
        content?: {
            id?: number,
            url?: string,
            text?: string,
            joined?: number,
            description?: string,
            location?: string,
            date?: string
        }
    },
    comments?: [{
        id?: number,
        user?: {
            id?: number,
            username?: string
        },
        date?: string,
        text?: string,
        likes?: number,
        dislikes?: number
    }]
}