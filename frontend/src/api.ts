export type DueCard = {
    cardId: number
    text: string
    definition: string
    example: string
}

export type Rating = 1 | 2 | 3 | 4

export type ReviewResult = {
    cardId: number
    state: string
    nextDue: string
}

async function request<T>(path: string, init?: RequestInit): Promise<T> {
    const res = await fetch(`/api${path}`, {
        headers: { 'Content-Type': 'application/json' },
        ...init,
    })
    if(!res.ok) {
        throw new Error(`${res.status} ${res.statusText}`)
    }
    return res.json() as Promise<T>
}

export const api = {
    getDueCards: () => request<DueCard[]>('/reviews/due'),
    review: (cardId: number, rating: Rating) =>
        request<ReviewResult>('/reviews', {
            method: 'POST',
            body: JSON.stringify({ cardId, rating })
        }),
}