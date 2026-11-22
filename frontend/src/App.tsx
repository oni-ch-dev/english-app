import { useState } from 'react'
import { ReviewSession } from './ReviewSession'
import { AddExpressionForm } from './AddExpressionForm'
import './App.css'


function App() {
  const [view, setView] = useState<'review' | 'add'>('review')

  return (
    <main>
      <nav>
        <button onClick={() => setView('review')}>Review</button>
        <button onClick={() => setView('add')}>Add</button>
      </nav>
      {view === 'review' ? (
        <>
          <h1>Review</h1>
          <ReviewSession />
        </>
      ) : (
        <>
          <h1>Add Expression</h1>
          <AddExpressionForm />
        </>
      )}
    </main>
  )
}

export default App
