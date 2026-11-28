import { useEffect, useState } from 'react';
import { api, type DueCard, type Rating } from './api'

const RATINGS: { value: Rating; label: string }[] = [
    { value: 1, label: 'Again' },
    { value: 2, label: 'Hard' },
    { value: 3, label: 'Good' },
    { value: 4, label: 'Easy' },
]

export function ReviewSession() {
    const [cards, setCards ] = useState<DueCard[] | null>(null)
    const [index, setIndex] = useState(0)
    const [revealed, setRevealed] = useState(false)
    const [submitting , setSubmitting] = useState(false)
    const [error, setError] = useState<string | null>(null)

    useEffect(() => {
        api.getDueCards()
            .then(setCards)
            .catch((e: Error) => setError(e.message))
    }, [])

    async function handleRate(rating: Rating){
        if(!cards) return
        setSubmitting(true)
        try {
            await api.review(cards[index].cardId, rating)
            setIndex((i) => i + 1)
            setRevealed(false)
        } catch(e) {
            setError((e as Error).message)
        } finally {
            setSubmitting(false)
        }
    }

    if(error) return <p>Error:{error}</p>
    if(cards === null) return <p>Loading...</p>
    if(index >= cards.length) return <p>All done for today!</p>

    const card = cards[index]

    return (
        <div className="card">
            <p className="progress">{index + 1} / {cards.length}</p>
            <p className="definition">{card.definition}</p>
            
            {revealed ? (
                <>
                    <h2 className="answer">{card.text}</h2>
                    {card.example && <p className="example"><em>{card.example}</em></p>}
                    <div className="ratings">
                        {RATINGS.map((r) => (
                            <button
                                key={r.value}
                                className={`rating-${r.value}`}
                                disabled={submitting}
                                onClick={() => handleRate(r.value)}
                            >
                                {r.label}
                            </button>
                        ))}
                    </div>
                </>
            ) : (
                <button className="show-answer" onClick={() => setRevealed(true)}>Show Answer</button>
            )}
        </div>
    )
}