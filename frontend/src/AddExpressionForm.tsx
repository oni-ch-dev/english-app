import { useState, type SubmitEvent } from 'react'
import { api } from './api'

export function AddExpressionForm() {
    const [text, setText] = useState('')
    const [definition, setDefinition] = useState('')
    const [example, setExample] = useState('')
    const [submitting, setSubmitting] = useState(false)
    const [message, setMessage] = useState<string | null>(null)

    async function handleSubmit(e: SubmitEvent<HTMLFormElement>){
        e.preventDefault()
        setSubmitting(true)
        setMessage(null)
        try {
            const created = await api.createExpression({
                text: text.trim(),
                definition: definition.trim() || undefined,
                example: example.trim() || undefined,
            })
            setMessage(`Saved: ${created.text}`)
            setText('')
            setDefinition('')
            setExample('')
        } catch(e){
            setMessage(`Error: ${(e as Error).message}`)
        } finally {
            setSubmitting(false)
        }
    }
    
    return (
        <form className="form card" onSubmit={handleSubmit}>
            <label>
                Expression
                <input
                    value={text}
                    onChange={(e) => setText(e.target.value)}
                    required
                    maxLength={500}
                />
            </label>
            <label>
                Definition
                <textarea value={definition} onChange={(e) => setDefinition(e.target.value)} />
            </label>
            <label>
                Example
                <textarea value={example} onChange={(e) => setExample(e.target.value)} />
            </label>
            <button type="submit" disabled={submitting || !text.trim()}>Add</button>
            {message && <p>{message}</p>}
        </form>
    )
}